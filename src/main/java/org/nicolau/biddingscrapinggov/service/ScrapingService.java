package org.nicolau.biddingscrapinggov.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.nicolau.biddingscrapinggov.model.Bidding;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScrapingService {

    private static final int NUMBER_OF_RECORDS_PAGE = 20;
    private static final String URL = "http://comprasnet.gov.br/ConsultaLicitacoes/ConsLicitacaoDia.asp";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final BiddingService service;

    public  void  runScraping() throws Exception {
        log.info("ScrapingService.runScraping()");
        try {
            Document doc = Jsoup.connect(URL).get();
            String recordTotalText = doc.select("html > body > table.tex3 > tbody > tr").get(1).select("> td.td > center").text();
            //Pega o valor total de Licitações
            Integer recordTotalQuantity = loadTotalRecord(recordTotalText);
            int numberTotalOfPages = (recordTotalQuantity / NUMBER_OF_RECORDS_PAGE) + 1;

            for (int index = 1; index <= numberTotalOfPages; index++) {
                //Pega todos as Licitações
                Elements bidding = doc.select("html > body > table.tex3 > tbody > tr").get(0).select("> td > form");
                List<Bidding> biddingList = new ArrayList<>();
                bidding.forEach(bid -> {
                    Bidding newBidding = new Bidding();
                    Elements record = bid.select("> table > tbody > tr.tex3 > td");

                    loadBidding(record, newBidding);
                    biddingList.add(newBidding);
                });

                log.info("Salvando dados da página " + index);
                service.saveOrUpdate(biddingList);
                doc = Jsoup.connect(URL + "?pagina=" + index + 1).get();
            }
        } catch (Exception e) {
            log.info("Mandar email ao suporte: Alguma mudança pode ter impedido a leitura diária de dados. Por favor, verificar o mais rápido possível.");
        }
    }

        private void loadBidding (Elements record, Bidding bidding){
            loadInstitutionAndUASGCode(record, bidding);
            loadModalityAndNumber(record, bidding);
            loadObject(record, bidding);
            loadStartDateAndStartDateDetails(record, bidding);
            loadAddress(record, bidding);
            loadTelephone(record, bidding);
            loadFax(record, bidding);
            loadDeliveryDateProposalAndDeliveryDateProposalDetail(record, bidding);
        }
        private void loadInstitutionAndUASGCode (Elements record, Bidding bidding){
            List<String> values = Arrays.stream(record.select("> b").get(0).text().split("Código da UASG:")).toList();
            bidding.setInstitution(values.get(0).trim());
            bidding.setUASGCode(values.get(1).trim());
        }

        private void loadModalityAndNumber (Elements record, Bidding bidding){
            List<String> values = Arrays.stream(record.select("> b").get(1).text().split("Nº")).toList();
            bidding.setModality(values.get(0).trim());
            bidding.setNumber(values.get(1).split("-")[0].trim());
        }

        private void loadObject (Elements record, Bidding bidding){
            bidding.setObject(record.select("> b").get(2).nextSibling().toString().split("Objeto:")[1].trim());
        }

        private void loadStartDateAndStartDateDetails (Elements record, Bidding bidding){
            String[] values = record.select("> b").get(3).nextSibling().toString().split("das");
            values[0] = values[0].replace("&nbsp;", "").trim();

            bidding.setStartDate(LocalDate.parse(values[0], DATE_TIME_FORMATTER));
            bidding.setStartDateDetails(record.select("> b").get(3).nextSibling().toString().replace("&nbsp;", "").trim());
        }

        private void loadAddress (Elements record, Bidding bidding){
            bidding.setAddress(record.select("> b").get(4).nextSibling().toString().replace("&nbsp;", "").trim());
        }
        private void loadTelephone (Elements record, Bidding bidding){
            bidding.setTelephone(record.select("> b").get(5).nextSibling().toString().replace("&nbsp;", "").trim());
        }
        private void loadFax (Elements record, Bidding bidding){
            bidding.setFax(record.select("> b").get(6).nextSibling().toString().replace("&nbsp;", "").trim());
        }
        private void loadDeliveryDateProposalAndDeliveryDateProposalDetail (Elements record, Bidding bidding){
            String[] values = record.select("> b").get(7).nextSibling().toString().split("às");
            values[0] = values[0].replace("&nbsp;", "").trim();

            bidding.setDeliveryDateProposal(values.length > 1 ? LocalDate.parse(values[0], DATE_TIME_FORMATTER) : null);
            bidding.setDeliveryDateProposalDetails(record.select("> b").get(7).nextSibling().toString().replace("&nbsp;", "").trim());
        }

        private Integer loadTotalRecord (String recodTotalText){
            // Usando expressão regular para encontrar o último número
            Pattern pattern = Pattern.compile("\\d+");
            Matcher matcher = pattern.matcher(recodTotalText);
            Integer lastNumber = null;
            while (matcher.find()) {
                lastNumber = Integer.parseInt(matcher.group());
            }

            return lastNumber;
        }

}


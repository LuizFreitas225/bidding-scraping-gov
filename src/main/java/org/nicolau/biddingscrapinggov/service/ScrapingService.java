package org.nicolau.biddingscrapinggov.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.nicolau.biddingscrapinggov.model.Bidding;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ScrapingService {

    private static final int NUMBER_OF_RECORDS_PAGE = 20;
    private static final String URL = "http://comprasnet.gov.br/ConsultaLicitacoes/ConsLicitacaoDia.asp";

    public  void  runScraping() throws Exception {
        Document doc = Jsoup.connect(URL).get();
        String recordTotalText = doc.select("html > body > table.tex3 > tbody > tr").get(1).select("> td.td > center").text();
        //Pega o valor total de Licitações
        Integer recordTotalQuantity = loadTotalRecord(recordTotalText);
        int numberTotalOfPages = ( recordTotalQuantity / NUMBER_OF_RECORDS_PAGE ) + 1;

        for (int index = 1; index < numberTotalOfPages ; index++){
            String title = doc.title();
            //Pega todos as Licitações
            Elements bidding = doc.select("html > body > table.tex3 > tbody > tr").get(0).select("> td > form");

            bidding.forEach(bid -> {
                Bidding newBidding =  new Bidding();
                Elements record = bid.select("> table > tbody > tr.tex3 > td");
                loadInstitutionAndUASGCode(record, newBidding);
                loadModalityAndNumber(record, newBidding);
            });

            doc = Jsoup.connect(URL + "?pagina=" + index).get();

        }

    }

    private void loadInstitutionAndUASGCode(Elements record, Bidding bidding){
        List<String> values =  Arrays.stream(record.select("> b").get(0).text().split("Código da UASG:")).toList();
        bidding.setInstitution(values.get(0).trim());
        bidding.setUASGCode(values.get(1).trim());
    }

    private void loadModalityAndNumber(Elements record, Bidding bidding){
        List<String> values =  Arrays.stream(record.select("> b").get(1).text().split("Nº")).toList();
        bidding.setModality(values.get(0).trim());
        bidding.setModality(values.get(1).trim());
    }

    private Integer loadTotalRecord(String recodTotalText){
        // Usando expressão regular para encontrar o último número
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(recodTotalText);
        return Integer.getInteger(matcher.group());
    }
}


package org.nicolau.biddingscrapinggov.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class ScrapingService {

    public void  runScraping() throws IOException {
        Document doc = Jsoup.connect("http://comprasnet.gov.br/ConsultaLicitacoes/ConsLicitacaoDia.asp").get();
        Elements links = doc.select("a");

    }

    public static void main(String[] args) throws Exception {
        Document doc = Jsoup.connect("http://comprasnet.gov.br/ConsultaLicitacoes/ConsLicitacaoDia.asp").get();
        String title = doc.title();
        //cria um loop que armazena os intens do seletor xpath//html/body/table[4]/tbody/tr[1]/td/form[1]/table/tbody
        Elements aux10 = doc.select("html > body > table.tex3 > form");

        for (int i = 1; i < 10; i++) {
            Element td = aux10
                    .get(i)
                    .select("tr.tex3")
                    .select("td")
                    .select("td")
                    .first();


            Map<String, String> data = new HashMap<>();
            String empresaText = td.select("b").first().text();
            if (empresaText.contains("Código da UASG:")) {
                data.put("Empresa", empresaText.split("Código da UASG:")[0].trim());
                data.put("Código da UASG", empresaText.split("Código da UASG:")[1].trim());
            }

            String pregaoText = td.select("b").get(1).text();
            if (pregaoText.contains("Pregão Eletrônico Nº")) {
                data.put("Pregão Eletrônico Nº", pregaoText.split("Pregão Eletrônico Nº   ")[1].trim());
            }
            System.out.println(data);
        }
        System.out.println("Title: " + title);

    }
}


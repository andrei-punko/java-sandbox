package by.andd3dfx.sitesparsing.onlinerby;

import by.andd3dfx.sitesparsing.onlinerby.dto.CpuItem;
import by.andd3dfx.sitesparsing.onlinerby.dto.CpuSearchResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CpuSearchUtil {

    private static final String CPU_URL_TEMPLATE = "https://catalog.onliner.by/sdapi/catalog.api/search/cpu?page=%d";
    private static final ObjectMapper mapper = new ObjectMapper();

    public CpuSearchResult extract(int pageNumber) throws IOException {
        String urlSpec = String.format(CPU_URL_TEMPLATE, pageNumber);

        Map items = mapper.readValue(new URL(urlSpec), Map.class);
        List<Map> products = (List<Map>) items.get("products");

        List<CpuItem> cpuItems = products.stream().map(product -> {
            String name = String.valueOf(product.get("name"));
            String url = String.valueOf(product.get("html_url"));
            Double price = Double.parseDouble(String.valueOf(((Map) ((Map) product.get("prices")).get("price_min")).get("amount")));
            return new CpuItem(name, url, price);
        }).collect(Collectors.toList());
        Integer lastPageNumber = (Integer) ((Map) items.get("page")).get("last");

        return new CpuSearchResult(cpuItems, lastPageNumber);
    }
}

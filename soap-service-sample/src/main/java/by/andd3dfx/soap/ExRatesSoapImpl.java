package by.andd3dfx.soap;

import by.nbrb.CurrenciesRef2Response;
import by.nbrb.CurrenciesRefDailyResponse;
import by.nbrb.CurrenciesRefMonthlyResponse;
import by.nbrb.CurrenciesRefResponse;
import by.nbrb.ExRatesDaily2Response;
import by.nbrb.ExRatesDailyResponse;
import by.nbrb.ExRatesDynResponse;
import by.nbrb.ExRatesMonthly2Response;
import by.nbrb.ExRatesMonthlyResponse;
import by.nbrb.IngotsPricesResponse;
import by.nbrb.MetalsPricesResponse;
import by.nbrb.MetalsRefResponse;
import by.nbrb.RefRateDynResponse;
import by.nbrb.RefRateOnDateResponse;
import by.nbrb.StockAddRatesResponse;
import by.nbrb.ExRatesSoap;
import jakarta.jws.WebService;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

@WebService(
        endpointInterface = "by.nbrb.ExRatesSoap",
        targetNamespace = "https://www.nbrb.by/",
        serviceName = "ExRates",
        portName = "ExRatesSoap"
)
public class ExRatesSoapImpl implements ExRatesSoap {

    @Override
    public ExRatesDailyResponse.ExRatesDailyResult exRatesDaily(XMLGregorianCalendar onDate) {
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

            Element dataSet = document.createElement("NewDataSet");
            document.appendChild(dataSet);

            dataSet.appendChild(rateRow(document, onDate, "USD", "3.2500"));
            dataSet.appendChild(rateRow(document, onDate, "EUR", "3.5100"));

            ExRatesDailyResponse.ExRatesDailyResult result = new ExRatesDailyResponse.ExRatesDailyResult();
            result.getAny().add(dataSet);
            return result;
        } catch (ParserConfigurationException e) {
            throw new IllegalStateException("Failed to build ExRatesDaily response", e);
        }
    }

    private static Element rateRow(Document document, XMLGregorianCalendar onDate, String abbreviation, String rate) {
        Element row = document.createElement("DailyExRatesOnDate");

        Element date = document.createElement("Date");
        date.setTextContent(onDate != null ? onDate.toXMLFormat() : "");
        row.appendChild(date);

        Element curAbbreviation = document.createElement("Cur_Abbreviation");
        curAbbreviation.setTextContent(abbreviation);
        row.appendChild(curAbbreviation);

        Element officialRate = document.createElement("Cur_OfficialRate");
        officialRate.setTextContent(rate);
        row.appendChild(officialRate);

        return row;
    }

    @Override
    public XMLGregorianCalendar lastDailyExRatesDate() {
        throw unsupported("LastDailyExRatesDate");
    }

    @Override
    public ExRatesDynResponse.ExRatesDynResult exRatesDyn(int curId, XMLGregorianCalendar fromDate, XMLGregorianCalendar toDate) {
        throw unsupported("ExRatesDyn");
    }

    @Override
    public MetalsRefResponse.MetalsRefResult metalsRef() {
        throw unsupported("MetalsRef");
    }

    @Override
    public XMLGregorianCalendar metalsLastDate() {
        throw unsupported("MetalsLastDate");
    }

    @Override
    public XMLGregorianCalendar lastMonthlyExRatesDate() {
        throw unsupported("LastMonthlyExRatesDate");
    }

    @Override
    public CurrenciesRefMonthlyResponse.CurrenciesRefMonthlyResult currenciesRefMonthly() {
        throw unsupported("CurrenciesRefMonthly");
    }

    @Override
    public IngotsPricesResponse.IngotsPricesResult ingotsPrices(XMLGregorianCalendar onDate) {
        throw unsupported("IngotsPrices");
    }

    @Override
    public XMLGregorianCalendar stockAddRatesLastDate() {
        throw unsupported("StockAddRatesLastDate");
    }

    @Override
    public CurrenciesRefResponse.CurrenciesRefResult currenciesRef(int periodicity) {
        throw unsupported("CurrenciesRef");
    }

    @Override
    public XMLGregorianCalendar ingotsLastDate() {
        throw unsupported("IngotsLastDate");
    }

    @Override
    public CurrenciesRefDailyResponse.CurrenciesRefDailyResult currenciesRefDaily() {
        throw unsupported("CurrenciesRefDaily");
    }

    @Override
    public ExRatesDaily2Response.ExRatesDaily2Result exRatesDaily2(XMLGregorianCalendar onDate) {
        throw unsupported("ExRatesDaily2");
    }

    @Override
    public RefRateOnDateResponse.RefRateOnDateResult refRateOnDate(XMLGregorianCalendar onDate) {
        throw unsupported("RefRateOnDate");
    }

    @Override
    public StockAddRatesResponse.StockAddRatesResult stockAddRates(XMLGregorianCalendar onDate) {
        throw unsupported("StockAddRates");
    }

    @Override
    public XMLGregorianCalendar startDate(int periodicity) {
        throw unsupported("StartDate");
    }

    @Override
    public ExRatesMonthlyResponse.ExRatesMonthlyResult exRatesMonthly(XMLGregorianCalendar onDate) {
        throw unsupported("ExRatesMonthly");
    }

    @Override
    public RefRateDynResponse.RefRateDynResult refRateDyn() {
        throw unsupported("RefRateDyn");
    }

    @Override
    public MetalsPricesResponse.MetalsPricesResult metalsPrices(int metalId, XMLGregorianCalendar fromDate, XMLGregorianCalendar toDate) {
        throw unsupported("MetalsPrices");
    }

    @Override
    public ExRatesMonthly2Response.ExRatesMonthly2Result exRatesMonthly2(XMLGregorianCalendar onDate) {
        throw unsupported("ExRatesMonthly2");
    }

    @Override
    public CurrenciesRef2Response.CurrenciesRef2Result currenciesRef2(int periodicity) {
        throw unsupported("CurrenciesRef2");
    }

    private static UnsupportedOperationException unsupported(String operation) {
        return new UnsupportedOperationException(operation + " is not implemented");
    }
}

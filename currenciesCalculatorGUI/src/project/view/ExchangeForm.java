package project.view;

import org.xml.sax.SAXException;
import project.collections.CurrencyDataCollection;
import project.collections.IDataCollection;
import project.collections.providers.IStringCurrencyCollectionProvider;
import project.collections.providers.XMLCurrencyCollectionProvider;
import project.currency.ICurrency;
import project.data.IRemoteDataProvider;
import project.data.RemoteDataProvider;
import project.exchange.Exchange;
import project.exchange.IExchange;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public class ExchangeForm {
    private JPanel mainPanel;
    private JComboBox<ICurrency> SourceCurrencyComboBox;
    private JComboBox<ICurrency> DestinationCurrencyComboBox;
    private JTextField quantityTextField;
    private JTextField resultTextField;
    private JButton exchangeButton;

    private IRemoteDataProvider provider;
    private IDataCollection LastA;
    private IStringCurrencyCollectionProvider xmlProvider;
    private IExchange exchange;

    public ExchangeForm() {
        exchangeButton.addActionListener(e -> {
            try {
                double value;
                String sVal = quantityTextField.getText();
                value = Math.abs(Double.parseDouble(sVal));

                ICurrency from = (ICurrency) SourceCurrencyComboBox.getSelectedItem();
                ICurrency to = (ICurrency) DestinationCurrencyComboBox.getSelectedItem();
                double result = exchange.exchange(from, to, value);
                resultTextField.setText(Double.toString(result));
                quantityTextField.setText(Double.toString(value));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Zly format liczby");
            }
        });
    }

    public static void initRates(ExchangeForm frm) {
        frm.provider = new RemoteDataProvider();
        frm.LastA = new CurrencyDataCollection();
        frm.xmlProvider = new XMLCurrencyCollectionProvider();
        frm.exchange = new Exchange();
    }

    public static void requireDefault(ExchangeForm frm) {
        try {
            String result = frm.provider.acquireRemoteData("https://www.nbp.pl/kursy/xml/LastA.xml");
            frm.xmlProvider.provide(result, frm.LastA);
        } catch (IOException | SAXException | ParserConfigurationException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public static void initForm(ExchangeForm frm) {
        List<ICurrency> clist = frm.LastA.getCurrencyList();
        for (ICurrency iCurrency : clist) {
            frm.SourceCurrencyComboBox.addItem(iCurrency);
            frm.DestinationCurrencyComboBox.addItem(iCurrency);
        }
    }

    public static void main(String[] args) {
        JFrame mainFrame = new JFrame("Kantor wymiany walut");
        ExchangeForm form = new ExchangeForm();
        mainFrame.setContentPane(form.mainPanel);
        initRates(form);
        requireDefault(form);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setVisible(true);
        initForm(form);
    }


}

package shop.task_2.model;

import java.io.Serializable;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public class Currency implements Serializable {
    private static final long serialVersionUID = 2L;
    private String currencyName;
    private int currencyGain;
    private String currencyCode;

    public Currency(String currencyName, int currencyGain, String currencyCode) {
        this.currencyName = currencyName;
        this.currencyGain = currencyGain;
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public int getCurrencyGain() {
        return currencyGain;
    }

    public void setCurrencyGain(int currencyGain) {
        this.currencyGain = currencyGain;
    }
}

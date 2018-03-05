package com.dreamingwell.xchange.stream.tester;

import info.bitrich.xchangestream.core.ProductSubscription;
import info.bitrich.xchangestream.gemini.GeminiStreamingExchange;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.currency.CurrencyPair;

import java.util.ArrayList;
import java.util.Collection;

public class GeminiStreamTesterMain extends XchangeStreamTesterMain {

    public static void main(String[] args) {
        new GeminiStreamTesterMain().run();
    }

    @Override
    protected ExchangeSpecification getExchangeSpecification() {
        return new ExchangeSpecification(GeminiStreamingExchange.class);
    }

    @Override
    protected Collection<CurrencyPair> getCurrencyPairs() {
        return new ArrayList<CurrencyPair>(){{
            add(CurrencyPair.ETH_USD);
            add(CurrencyPair.BTC_USD);
            add(CurrencyPair.ETH_BTC);
        }};
    }

    @Override
    public Object[] getStreamingOrderBookArguments() {
        return new Object[0];
    }

    @Override
    protected ProductSubscription[] getStreamProductSubscriptions() {
        return new ProductSubscription[0];
    }
}

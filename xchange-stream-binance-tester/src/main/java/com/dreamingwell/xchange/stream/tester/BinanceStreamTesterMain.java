package com.dreamingwell.xchange.stream.tester;

import info.bitrich.xchangestream.binance.BinanceStreamingExchange;
import info.bitrich.xchangestream.core.ProductSubscription;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.currency.CurrencyPair;

import java.util.ArrayList;
import java.util.Collection;

public class BinanceStreamTesterMain extends XchangeStreamTesterMain {

    public static void main(String[] args) {
        new BinanceStreamTesterMain().run();
    }


    @Override
    protected ExchangeSpecification getExchangeSpecification() {
        return new ExchangeSpecification(BinanceStreamingExchange.class);
    }

    @Override
    protected Collection<CurrencyPair> getCurrencyPairs() {
        return new ArrayList<CurrencyPair>(){{
            add(CurrencyPair.BCH_BTC);
            add(CurrencyPair.BCH_USD);
            add(CurrencyPair.BCH_ETH);
            add(CurrencyPair.BTC_USD);
            add(CurrencyPair.LTC_USD);
            add(CurrencyPair.LTC_BTC);
            add(CurrencyPair.ETH_USD);
            add(CurrencyPair.ETH_BTC);
        }};
    }

    @Override
    public Object[] getStreamingOrderBookArguments() {
        return new Object[]{100};
    }


    @Override
    public ProductSubscription[] getStreamProductSubscriptions() {

        // Define product subscriptions
        ProductSubscription.ProductSubscriptionBuilder productSubscriptionBuilder = ProductSubscription.create();

        for(CurrencyPair currencyPair : getCurrencyPairs()) {
            productSubscriptionBuilder.addOrderbook(currencyPair).build();
        }

        return new ProductSubscription[]{productSubscriptionBuilder.build()};

    }

}

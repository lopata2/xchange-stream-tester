package com.dreamingwell.xchange.stream.tester;

import info.bitrich.xchangestream.core.ProductSubscription;
import info.bitrich.xchangestream.core.StreamingExchange;
import info.bitrich.xchangestream.core.StreamingExchangeFactory;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.currency.CurrencyPair;

import java.math.BigDecimal;
import java.util.Collection;

public abstract class XchangeStreamTesterMain implements Runnable {


    public void run() {

        StreamingExchange exchange = StreamingExchangeFactory.INSTANCE.createExchange(getExchangeSpecification());

        exchange.connect(getStreamProductSubscriptions()).blockingAwait();


        getCurrencyPairs().forEach(
            currencyPair -> exchange.getStreamingMarketDataService()
                .getOrderBook(currencyPair, getStreamingOrderBookArguments())
                .subscribe(
                    orderBook -> {

                        if(orderBook.getAsks().size() == 0
                            || orderBook.getBids().size() == 0)
                            return;

                        BigDecimal lowestAsk = orderBook.getAsks().get(0).getLimitPrice();

                        BigDecimal highestBid = orderBook.getBids().get(0).getLimitPrice();

                        if(lowestAsk.compareTo(highestBid) < 0) {
                            System.err.println("Ask of " + lowestAsk + " is lower than highest bid of " + highestBid);
                        }

                    }
            )
        );

        while(!Thread.interrupted()) {
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
            }
        }

        exchange.disconnect();


    }

    protected abstract ProductSubscription[] getStreamProductSubscriptions();

    protected abstract ExchangeSpecification getExchangeSpecification();

    protected abstract Collection<CurrencyPair> getCurrencyPairs();

    public abstract Object[] getStreamingOrderBookArguments();
}

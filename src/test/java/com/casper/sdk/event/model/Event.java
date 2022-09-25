package com.casper.sdk.event.model;

import com.casper.sdk.model.clvalue.CLValueMap;
import com.casper.sdk.model.clvalue.CLValueU512;
import com.casper.sdk.model.clvalue.cltype.CLTypeString;
import org.junit.jupiter.api.Test;

public class Event {

    @Test
    void test() {
        String bytes = "0400000015000000636f6e74726163745f7061636b6167655f6861736840000000363263323762643864393634616461366331336337663263323435353764363263353430323432313362656131623064343035633766343433613366666264650a0000006576656e745f747970650e00000063657034375f6d696e745f6f6e6509000000726563697069656e744e0000004b65793a3a4163636f756e7428323566363365363732303138636637333166643036383764383437373566363036376462373932313965616637356563373031346561363637323733636465632908000000746f6b656e5f69642400000062383738623136302d353765382d346339302d613937642d396561613636363936373363";
        CLValueMap clValueString = new CLValueMap(new CLTypeString(), new CLTypeString());
        clValueString.setJsonBytes(bytes);
        System.out.println(clValueString.getValue());
    }
    @Test
    void test2() {
        String bytes = "0600c91b886109";
        CLValueU512 clValueString = new CLValueU512();
        clValueString.setJsonBytes(bytes);
        System.out.println(clValueString.getValue());
    }
}

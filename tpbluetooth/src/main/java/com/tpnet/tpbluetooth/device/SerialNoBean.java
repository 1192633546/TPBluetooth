package com.tpnet.tpbluetooth.device;

/**
 * author : dingqb
 * e-mail : dingqb@sany.com.cn
 * date   : 2022/1/12 6:54 PM
 * desc   :
 * version: 1.0
 */
public class SerialNoBean {
    String machineNum1;
    String machineNum2;
    String machineNum3;
    String machineNum4;
    String machineNum5;
    String machineNum6;
    String machineNum7;
    String machineNum8;
    String machineNum9;
    String machineNum10;
    String machineNum11;
    String machineNum12;
    String machineNum13;

    String serialNo;

    private SerialNoBean(Builder builder) {
        machineNum1 = builder.machineNum1;
        machineNum2 = builder.machineNum2;
        machineNum3 = builder.machineNum3;
        machineNum4 = builder.machineNum4;
        machineNum5 = builder.machineNum5;
        machineNum6 = builder.machineNum6;
        machineNum7 = builder.machineNum7;
        machineNum8 = builder.machineNum8;
        machineNum9 = builder.machineNum9;
        machineNum10 = builder.machineNum10;
        machineNum11 = builder.machineNum11;
        machineNum12 = builder.machineNum12;
        machineNum13 = builder.machineNum13;
    }

    public String getSerialNo() {
        serialNo = machineNum1
                .concat(machineNum2)
                .concat(machineNum3)
                .concat(machineNum4)
                .concat(machineNum5)
                .concat(machineNum6)
                .concat(machineNum7)
                .concat(machineNum8)
                .concat(machineNum9)
                .concat(machineNum10)
                .concat(machineNum11)
                .concat(machineNum12)
                .concat(machineNum13);
        return serialNo;
    }


    public static final class Builder {
        private String machineNum1;
        private String machineNum2;
        private String machineNum3;
        private String machineNum4;
        private String machineNum5;
        private String machineNum6;
        private String machineNum7;
        private String machineNum8;
        private String machineNum9;
        private String machineNum10;
        private String machineNum11;
        private String machineNum12;
        private String machineNum13;

        public Builder() {
        }

        public Builder machineNum1(String val) {
            machineNum1 = val;
            return this;
        }

        public Builder machineNum2(String val) {
            machineNum2 = val;
            return this;
        }

        public Builder machineNum3(String val) {
            machineNum3 = val;
            return this;
        }

        public Builder machineNum4(String val) {
            machineNum4 = val;
            return this;
        }

        public Builder machineNum5(String val) {
            machineNum5 = val;
            return this;
        }

        public Builder machineNum6(String val) {
            machineNum6 = val;
            return this;
        }

        public Builder machineNum7(String val) {
            machineNum7 = val;
            return this;
        }

        public Builder machineNum8(String val) {
            machineNum8 = val;
            return this;
        }

        public Builder machineNum9(String val) {
            machineNum9 = val;
            return this;
        }

        public Builder machineNum10(String val) {
            machineNum10 = val;
            return this;
        }

        public Builder machineNum11(String val) {
            machineNum11 = val;
            return this;
        }

        public Builder machineNum12(String val) {
            machineNum12 = val;
            return this;
        }

        public Builder machineNum13(String val) {
            machineNum13 = val;
            return this;
        }

        public SerialNoBean build() {
            return new SerialNoBean(this);
        }
    }
}

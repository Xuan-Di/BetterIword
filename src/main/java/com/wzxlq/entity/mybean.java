package com.wzxlq.entity;


public class mybean {

    /**
     * touser : OPENID
     * template_id : ngqIpbwh8bUfcSsECmogfXcV14J0tQlEpBO27izEYtY
     * data : {"first":{"value":"恭喜你购买成功！","color":"#173177"},"company":{"value":"巧克力","color":"#173177"},"time":{"value":"39.8元","color":"#173177"},"result":{"value":"2014年9月22日","color":"#173177"},"remark":{"value":"欢迎再次购买！","color":"#173177"}}
     */

    private String touser;
    private String template_id;
    private DataBean data;

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * first : {"value":"恭喜你购买成功！","color":"#173177"}
         * company : {"value":"巧克力","color":"#173177"}
         * time : {"value":"39.8元","color":"#173177"}
         * result : {"value":"2014年9月22日","color":"#173177"}
         * remark : {"value":"欢迎再次购买！","color":"#173177"}
         */

        private FirstBean first;
        private CompanyBean company;
        private TimeBean time;
        private ResultBean result;
        private RemarkBean remark;

        public FirstBean getFirst() {
            return first;
        }

        public void setFirst(FirstBean first) {
            this.first = first;
        }

        public CompanyBean getCompany() {
            return company;
        }

        public void setCompany(CompanyBean company) {
            this.company = company;
        }

        public TimeBean getTime() {
            return time;
        }

        public void setTime(TimeBean time) {
            this.time = time;
        }

        public ResultBean getResult() {
            return result;
        }

        public void setResult(ResultBean result) {
            this.result = result;
        }

        public RemarkBean getRemark() {
            return remark;
        }

        public void setRemark(RemarkBean remark) {
            this.remark = remark;
        }

        public static class FirstBean {
            /**
             * value : 恭喜你购买成功！
             * color : #173177
             */

            private String value;
            private String color;

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }
        }

        public static class CompanyBean {
            /**
             * value : 巧克力
             * color : #173177
             */

            private String value;
            private String color;

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }
        }

        public static class TimeBean {
            /**
             * value : 39.8元
             * color : #173177
             */

            private String value;
            private String color;

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }
        }

        public static class ResultBean {
            /**
             * value : 2014年9月22日
             * color : #173177
             */

            private String value;
            private String color;

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }
        }

        public static class RemarkBean {
            /**
             * value : 欢迎再次购买！
             * color : #173177
             */

            private String value;
            private String color;

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }
        }
    }
}

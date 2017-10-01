package ta.market.domain;

public class RequestParamFixture {

    public static RequestParam createRequestParameter() {
        return builder().build();
    }

    public static RequestParamFixtureBuilder builder() {
        return new RequestParamFixtureBuilder();
    }

    public static class RequestParamFixtureBuilder  {
        private String name = "param1";
        private String value = "value1";

        public RequestParam build() {
            return new RequestParam(name, value);
        }

        public RequestParamFixtureBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public RequestParamFixtureBuilder setValue(String value) {
            this.value = value;
            return this;
        }
    }
}

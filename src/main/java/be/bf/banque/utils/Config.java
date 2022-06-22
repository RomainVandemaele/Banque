package be.bf.banque.utils;

public class Config {
    public static class Db {
        private static final String DB_TYPE = "sqlite";
        private static final String DB_PATH = Db.class.getClassLoader().getResource("bank.sqlite3").toString();

        public static String getUrlSQLite() {
            StringBuilder builder = new StringBuilder();
            builder.append("jdbc:").append(DB_TYPE).append(":");
            builder.append(DB_PATH);
            return builder.toString();
        }

        public static String getUrlPostGres() {
            StringBuilder builder = new StringBuilder("jpa-demo");
            String os = System.getProperty("os.name").toLowerCase();
            if(!os.contains("windows")) {
                builder.append("-linux");
            }
            return builder.toString();
        }


    }
}


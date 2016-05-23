package com.zhlh.zeus.business.citycode;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;

public class CityCodeManager {
    private static HashMap<String, CityCode> codeBase = new HashMap<String, CityCode>();

    public static String getCityCodeByLicense(String license) {
        CityCode code = codeBase.get(license.substring(0, 2));
        if (code == null) {
            code = codeBase.get(license.substring(0, 1));
        }

        if (code == null) {
            return null;
        }
        return code.code;
    }


    public static void initialize(String filepath) {
        try {
            loadFromCSV(filepath);
        } catch (Exception e) {
            System.err.println("load CityCode data from CSV error:" + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void loadFromCSV(String filepath) throws IOException {

        File csvData = new File(filepath);
        CSVFormat fmt = CSVFormat.EXCEL.withDelimiter(';');
        CSVParser parser = CSVParser.parse(csvData, Charset.forName("GBK"), fmt);

        int sumCount = 0;

        for (CSVRecord csvRecord : parser) {
            sumCount++;
            try {
                CityCode code = new CityCode(csvRecord.get(0), csvRecord.get(1), csvRecord.get(2));
                CityCode other = codeBase.get(code.vehicleLicenseHeader);
                if (other == null) {
                    codeBase.put(code.vehicleLicenseHeader, code);
                } else {
                    if (!other.code.endsWith("0000")) {
                        if (code.code.endsWith("0000")) {
                            other.code = code.code;
                        }
                    }
                }
            } catch (Exception e) {
                System.err.println(csvRecord.get(0) + " : " + e.getMessage());
                continue;
            }
        }


    }

}

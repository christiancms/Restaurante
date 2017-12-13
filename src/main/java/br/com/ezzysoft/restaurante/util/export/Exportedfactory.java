package br.com.ezzysoft.restaurante.util.export;

import org.primefaces.component.export.*;

import javax.faces.FacesException;
/**
 * Created by christian on 28/11/17.
 */
public class Exportedfactory {

    public static Exporter getExporterForType(String type) {
        Exporter exporter = null;

        try {
            ExporterType exporterType = ExporterType.valueOf(type.toUpperCase());

            switch (exporterType) {
                case XLS:
                    exporter = new ExcelExporter();
                    break;

                case PDF:
                    exporter = new CustomPdfExporter();
                    break;

                case CSV:
                    exporter = new CSVExporter();
                    break;

                case XML:
                    exporter = new XMLExporter();
                    break;

                case XLSX:
                    exporter = new ExcelXExporter();
                    break;
                case XLSXSTREAM:
                    exporter = new ExcelXStreamExporter();
                    break;

            }
        } catch (IllegalArgumentException e) {
            throw new FacesException(e);
        }

        return exporter;
    }
}
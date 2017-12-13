package br.com.ezzysoft.restaurante.util.export;

/**
 * Created by christian on 28/11/17.
 */
public interface ExporterOptions {

    public String getFacetFontStyle();

    public String getFacetFontColor();

    public String getFacetBgColor();

    public String getFacetFontSize();

    public String getCellFontStyle();

    public String getCellFontColor();

    public String getCellFontSize();

    public float[] getColumnWidths();
}

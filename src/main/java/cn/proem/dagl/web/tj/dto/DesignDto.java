package cn.proem.dagl.web.tj.dto;

import java.util.List;
import java.util.Map;

public class DesignDto {
    /**
     * @return the pic
     */
    public List<SeriesDto> getPic() {
        return pic;
    }

    /**
     * @param pic the pic to set
     */
    public void setPic(List<SeriesDto> pic) {
        this.pic = pic;
    }

    /**
     * @return the report
     */
    public List<Map<String, Object>> getReport() {
        return report;
    }

    /**
     * @param report the report to set
     */
    public void setReport(List<Map<String, Object>> report) {
        this.report = report;
    }

    List<Map<String, Object>> report;
    List<SeriesDto> pic;
}

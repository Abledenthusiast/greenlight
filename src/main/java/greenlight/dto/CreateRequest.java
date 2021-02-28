package greenlight.dto;

public class CreateRequest {
    private byte[] pdf;

    public CreateRequest() {}

    public byte[] getPdfBytes() {
        return pdf;
    }

    public void setPdfBytes(byte[] pdf) {
        this.pdf = pdf;
    }
}

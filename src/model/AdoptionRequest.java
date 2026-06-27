package model;

public class AdoptionRequest {

    // ===== Private Fields of AdoptionRequest =====
    private int requestId;
    private int userId;
    private int petId;
    private String requestDate;
    private String status;
    
    // ===== Parameterized and Non-Parameterized Constructor of AdoptionRequest =====
    public AdoptionRequest() {    
    }
    
    public AdoptionRequest(int requestId, int userId,
                           int petId, String requestDate,
                           String status) {
        
        this.requestId = requestId;
        this.userId = userId;
        this.petId = petId;
        this.requestDate = requestDate;
        this.status = status;
    }

    // ===== Getter and Setter Method =====
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }
    
    
}

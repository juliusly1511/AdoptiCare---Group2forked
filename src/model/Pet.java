package model;

public class Pet {

    // ===== Private Fields/Attributes of Pet Class =====
    private int petId;
    private String petName;
    private String species;
    private String breed;
    private int age;
    private String healthCondition;
    private String vaccinationStatus;
    private String lastVaccinationDate;
    private String nextVaccinationSchedule;
    private String adoptionStatus;
    private boolean archived;
    
    // ===== Pet Constructor parameterized/non-parameterized =====
    public Pet() {
    }

    public Pet(int petId, String petName,
               String species, String breed,
               int age, String healthCondition,
               String vaccinationStatus, String lastVaccinationDate,
               String nextVaccinationSchedule, String adoptionStatus,
               boolean archived) {
        
        this.petId = petId;
        this.petName = petName;
        this.species = species;
        this.breed = breed;
        this.age = age;
        this.healthCondition = healthCondition;
        this.vaccinationStatus = vaccinationStatus;
        this.lastVaccinationDate = lastVaccinationDate;
        this.nextVaccinationSchedule = nextVaccinationSchedule;
        this.adoptionStatus = adoptionStatus;
        this.archived = archived;
        
    }
    
    // ===== Getter and Setter Method of Private Fields =====
    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getHealthCondition() {
        return healthCondition;
    }

    public void setHealthCondition(String healthCondition) {
        this.healthCondition = healthCondition;
    }

    public String getVaccinationStatus() {
        return vaccinationStatus;
    }

    public void setVaccinationStatus(String vaccinationStatus) {
        this.vaccinationStatus = vaccinationStatus;
    }

    public String getLastVaccinationDate() {
        return lastVaccinationDate;
    }

    public void setLastVaccinationDate(String lastVaccinationDate) {
        this.lastVaccinationDate = lastVaccinationDate;
    }

    public String getNextVaccinationSchedule() {
        return nextVaccinationSchedule;
    }

    public void setNextVaccinationSchedule(String nextVaccinationSchedule) {
        this.nextVaccinationSchedule = nextVaccinationSchedule;
    }

    public String getAdoptionStatus() {
        return adoptionStatus;
    }

    public void setAdoptionStatus(String adoptionStatus) {
        this.adoptionStatus = adoptionStatus;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    
}

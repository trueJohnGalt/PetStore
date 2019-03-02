package tests;

import com.google.common.collect.ImmutableList;
import endpoints.PetStoreEndPoints;
import models.pet.Category;
import models.pet.Pet;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;

public class SampleTestPetStore {


    public static final PetStoreEndPoints PET_STORE_END_POINTS = new PetStoreEndPoints();

    @Test
    public void verifyStatusCode(){
        PET_STORE_END_POINTS.getPetByStatus("sold")
                .then()
                .statusCode(200);
    }
    @Test
    public void verifyBody(){
        PET_STORE_END_POINTS.getPetByStatus("sold")
                .then()
                .assertThat()
                .body(is(not(isEmptyOrNullString())));
    }
    @Test
    public void verifyNotExisting(){
        PET_STORE_END_POINTS.getPetById("22")
                .then()
                .statusCode(404);
    }

    @Test
    public void verifyPetToBeCreated(){
        Category category = new Category();
        category.setName("Dogs");
        category.setId(666);

        Pet dog = new Pet();
        dog.setName("Max");
        dog.setCategory(category);
        dog.setPhotoUrls(ImmutableList.of("someUrls"));
        dog.setStatus("available");

        PET_STORE_END_POINTS.createPet(dog)
                .then()
                .statusCode(200);
    }

}

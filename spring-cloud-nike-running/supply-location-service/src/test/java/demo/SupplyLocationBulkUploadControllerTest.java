package demo;

import demo.domain.SupplyLocation;
import demo.domain.SupplyLocationRepository;
import demo.rest.SupplyLocationBulkUploadController;
import demo.service.SupplyLocationService;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SupplyLocationBulkUploadControllerTest {

    private SupplyLocationRepository repository;

    private SupplyLocationService service;

    private SupplyLocationBulkUploadController controller;

    private List<SupplyLocation> inputLocations;

    @Before
    public void setupMock() {
        repository = mock(SupplyLocationRepository.class);
        service = mock(SupplyLocationService.class);
        controller = new SupplyLocationBulkUploadController(repository, service);

        inputLocations = new ArrayList<>();
        inputLocations.add(generateSupplyLocations(4, 4, "504"));
        inputLocations.add(generateSupplyLocations(5, 5, "505"));
        inputLocations.add(generateSupplyLocations(6, 6, "506"));
    }

    @Test
    public void whenListFiltered_returnSavedList() {
        List<SupplyLocation> locations = new ArrayList<>();
        locations.add(generateSupplyLocations(4, 4, "504"));
        when(service.saveSupplyLocationsZipContains504(inputLocations)).thenReturn(locations);

        assertThat(controller.uploadFilteredLocations(inputLocations)).size().isEqualTo(1);;
        assertThat(controller.uploadFilteredLocations(inputLocations).get(0).getZip()).isEqualTo("504");
//        assertEquals(1, controller.uploadFilteredLocations(inputLocations).size());
//        assertEquals("504", controller.uploadFilteredLocations(inputLocations).get(0).getZip());
    }

    private SupplyLocation generateSupplyLocations(double latitude, double longitude, String zip) {
        SupplyLocation location = new SupplyLocation(latitude, longitude);
        location.setZip(zip);
        return location;
    }
}

package nl.pancompany.clean.architecture.usecase.test;

import nl.pancompany.clean.architecture.domain.port.out.DummyDisplayPort;
import nl.pancompany.clean.architecture.usecase.port.in.DisplayDummyUsecase;
import nl.pancompany.clean.architecture.usecase.port.in.SaveDummyUsecase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static nl.pancompany.clean.architecture.test.common.TestBuilders.dummy;
import static nl.pancompany.clean.architecture.test.common.TestData.DUMMY_FOR_DISPLAY_DATA;
import static nl.pancompany.clean.architecture.test.common.extractors.DummyExtractor.getDto;
import static org.mockito.Mockito.verify;

public class DisplayDummyTest {

    private DisplayDummyUsecase displayDummyUsecase;
    private SaveDummyUsecase saveDummyUsecase;
    private DummyDisplayPort dummyDisplayPort;

    @BeforeEach
    public void setUp() {
        var testContext = new UsecaseTestContext();
        displayDummyUsecase = testContext.getDisplayDummyUsecase();
        saveDummyUsecase = testContext.getSaveDummyUsecase();
        dummyDisplayPort = testContext.getDummyDisplayPort();
    }

    @Test
    public void requestsDummyDisplay() {
        var dummy = dummy().forDisplay().build();
        var dummyDto = getDto(dummy);
        saveDummyUsecase.saveDummy(dummy);

        displayDummyUsecase.display(dummyDto.dummyId());

        verify(dummyDisplayPort).display(dummyDto.dummyData());
    }
}
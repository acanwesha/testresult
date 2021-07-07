package com.resultcopy.rest.api;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.resultcopy.rest.api.factory.CopyResultsApiServiceFactory;
import com.resultcopy.rest.model.BabyResult;

/**
 * @author AC089545
 * Test case for {@link CopyResultsApi}
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(CopyResultsApiServiceFactory.class)
public class CopyResultsApiTest {
	@Mock
    SecurityContext securityContext;
    @Mock 
    BabyResult babyResult;
    @Mock
    Response responseMock;

    /**
     * Test response for the copy result API.
     * @throws NotFoundException NotFoundException.
     */
    @Test
    public void testCopyResultsPost() throws Exception {
    	PowerMockito.mockStatic(CopyResultsApiServiceFactory.class);
    	
    	CopyResultsApiService copyResultsApiServiceMock = mock(CopyResultsApiService.class);
    	when(CopyResultsApiServiceFactory.getCopyResultsApi()).thenReturn(copyResultsApiServiceMock);
        when(copyResultsApiServiceMock.copyResultsPost(any(BabyResult.class), any(SecurityContext.class))).thenReturn(responseMock);

        Response response = new CopyResultsApi().copyResultsPost(babyResult, securityContext);
        assertNotNull(response);
    }
}

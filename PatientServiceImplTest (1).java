package com.resultcopy.service.serviceimpl;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.resultcopy.BabyResultResponse;
import com.resultcopy.CategoryResponse;
import com.resultcopy.ChildResultResponse;
import com.resultcopy.PatientDetailsResponse;
import com.resultcopy.PatientResponse;
import com.resultcopy.PatientResultResponse;
import com.resultcopy.ResultResponse;
import com.resultcopy.service.impl.BabyResultDAOImpl;
import com.resultcopy.service.impl.CategoryDAOImpl;
import com.resultcopy.service.impl.ChildDAOImpl;
import com.resultcopy.service.impl.PatientDAOImpl;
import com.resultcopy.service.impl.ResultDAOImpl;

import lombok.ToString;

@ToString
@RunWith(PowerMockRunner.class)
@PrepareForTest(PatientServiceImpl.class)
public class PatientServiceImplTest {	

	@Test
	public void testGetPatientDetails() throws Exception {

		PatientDAOImpl patienDaoImpl = mock(PatientDAOImpl.class);
		ChildDAOImpl chilDaoImpl = mock(ChildDAOImpl.class);
		ResultDAOImpl resultDaoImpl = mock(ResultDAOImpl.class);
		BabyResultDAOImpl babyResultDAOImpl = mock(BabyResultDAOImpl.class);
		CategoryDAOImpl categoryDaoImpl = mock(CategoryDAOImpl.class);

		int patientId = 123;
		when(patienDaoImpl.getPatientById(patientId)).thenReturn(new PatientResponse());

		PatientDetailsResponse patientDetailsResponse = new PatientDetailsResponse();
		patientDetailsResponse.setId(patientId);
		List<PatientDetailsResponse> patientList = new ArrayList<>();
		patientList.add(patientDetailsResponse);
		when(chilDaoImpl.getPatientById(patientId)).thenReturn(patientList);

		CategoryResponse categoryResponse = new CategoryResponse();
		categoryResponse.setId(patientId);
		List<CategoryResponse> categoryList = new ArrayList<>();
		categoryList.add(categoryResponse);
		when(categoryDaoImpl.getCategories()).thenReturn(categoryList);
	
		BabyResultResponse babyResultResponse = new BabyResultResponse();
		babyResultResponse.setDateTime(new Date());
		when(babyResultDAOImpl.getBabyPatientByChildId(patientDetailsResponse.getId()))
			.thenReturn(babyResultResponse);
		
		ChildResultResponse childResultResponse = new ChildResultResponse();
		childResultResponse.setResultId(patientId);
		childResultResponse.setValue("TEST_VALUE");
		List<ChildResultResponse> childList = new ArrayList<>();
		childList.add(childResultResponse);
		when(resultDaoImpl.getChildValueById(patientId)).thenReturn(childList);
		
		ResultResponse resultResponse = new ResultResponse();
		resultResponse.setId(patientId);
		resultResponse.setValue("TEST_VALUE");
		List<ResultResponse> resultList = new ArrayList<>();
		resultList.add(resultResponse);
		when(resultDaoImpl.getCategories(categoryResponse.getId())).thenReturn(resultList);
		
		PatientResultResponse result = new PatientServiceImpl(patienDaoImpl, chilDaoImpl, resultDaoImpl, categoryDaoImpl, babyResultDAOImpl)
				.getPatientDetails("123");
		Assertions.assertNotNull(result);
		
		when(babyResultDAOImpl.getBabyPatientByChildId(patientDetailsResponse.getId())).thenReturn(null);
		result = new PatientServiceImpl(patienDaoImpl, chilDaoImpl, resultDaoImpl, categoryDaoImpl, babyResultDAOImpl)
				.getPatientDetails("123");
		Assertions.assertNotNull(result);
	}
	
	@Test
    public void testConstructor() throws Exception {
		PatientDAOImpl patienDaoImpl = mock(PatientDAOImpl.class);
		ChildDAOImpl chilDaoImpl = mock(ChildDAOImpl.class);
		ResultDAOImpl resultDaoImpl = mock(ResultDAOImpl.class);
		BabyResultDAOImpl babyResultDAOImpl = mock(BabyResultDAOImpl.class);
		CategoryDAOImpl categoryDaoImpl = mock(CategoryDAOImpl.class);

		PowerMockito.whenNew(PatientDAOImpl.class).withNoArguments().thenReturn(patienDaoImpl);
		PowerMockito.whenNew(ChildDAOImpl.class).withNoArguments().thenReturn(chilDaoImpl);
		PowerMockito.whenNew(ResultDAOImpl.class).withNoArguments().thenReturn(resultDaoImpl);
		PowerMockito.whenNew(CategoryDAOImpl.class).withNoArguments().thenReturn(categoryDaoImpl);
		PowerMockito.whenNew(BabyResultDAOImpl.class).withNoArguments().thenReturn(babyResultDAOImpl);

		PatientServiceImpl result = new PatientServiceImpl();
		Assertions.assertNotNull(result);
    }

}

package com.oot.Recruitment.Utils;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import com.oot.Recruitment.Enumerated.AgeRange;
import com.oot.Recruitment.Enumerated.EducationType;
import com.oot.Recruitment.Enumerated.EnglishProficiencyLevel;
import com.oot.Recruitment.Models.Applicant;
import com.oot.Recruitment.Models.EOI;
import com.oot.Recruitment.Models.Education;
import com.oot.Recruitment.Models.Employment;

public final class Utils {
	public static AgeRange calculateAgeRange(Applicant applicant) {
		LocalDate now = LocalDate.now();
		LocalDate b_date = applicant.getBirthdate().toLocalDate();
		int age = Period.between(b_date, now).getYears();
		
		if(age >=18 && age <25) {
			return AgeRange.BETWEEN_18_TO_24;
		}
		else if(age >=25 && age <33) {
			return AgeRange.BETWEEN_25_TO_32;
		}
		else if(age >=33 && age <40) {
			return AgeRange.BETWEEN_33_TO_39;
		}
		else if(age >=40 && age <46) {
			return AgeRange.BETWEEN_40_TO_45;
		}
		else {
			return null;
		}
	}
	
	public static int calculateTotalPointsApprenticeship(EOI submitted) {
		int total_points=15;
		total_points+=calculateTotalPoints(submitted);
		return total_points;
	}
	
	public static int calculateTotalPointsPermanentReturnPosition(EOI submitted) {
		int total_points=5;
		total_points+=calculateTotalPoints(submitted);
		return total_points;
	}
	
	public static int calculateTotalPointsPermanentPosition(EOI submitted) {
		int total_points=0;
		total_points+=calculateTotalPoints(submitted);
		return total_points;
	}
	
	public static int calculateTotalPoints(EOI submitted) {
		int total_points=0;
		
		AgeRange age = submitted.getApplicant().getAgeRange();
		switch(age) {
			case BETWEEN_18_TO_24: total_points+=25;break;
			case BETWEEN_25_TO_32: total_points+=30;break;
			case BETWEEN_33_TO_39: total_points+=25;break;
			case BETWEEN_40_TO_45: total_points+=15;break;
			default:break;
		}
		
		EducationType highest_education_type = null;
		List<Education> all_education = submitted.getEducation();
		for(Education edu: all_education) {
			EducationType edu_type =edu.getEducation_type();
			if(edu_type.equals(EducationType.PHD)) {
				highest_education_type=EducationType.PHD;
				break;
			}
			else if(edu_type.equals(EducationType.BACHELOR_OR_ABOVE) && (highest_education_type==null || highest_education_type.equals(EducationType.DIPLOMA))) {
				highest_education_type=EducationType.BACHELOR_OR_ABOVE;
			}
			else if(edu_type.equals(EducationType.DIPLOMA) && (highest_education_type==null)) {
				highest_education_type=EducationType.DIPLOMA;
			}
		}
		switch(highest_education_type) {
			case PHD: total_points+=20;break;
			case BACHELOR_OR_ABOVE: total_points+=15;break;
			case DIPLOMA: total_points+=10;break;
			default:break;
		}
		
		EnglishProficiencyLevel english_lv = submitted.getEnglish_proficiency_level();
		switch(english_lv) {
			case COMPETENT: total_points+=0;break;
			case PROFICIENCT: total_points+=10;break;
			case SUPERIOR: total_points+=20;break;
			default:break;
		}
		
		total_points+=calculateWorkingExperiencePoint(submitted.getEmployment());

		return total_points;
	}
		
	public static int calculateWorkingExperiencePoint(List<Employment> employment_list) {
		int point = 0;
		int working_length_each_employment=0;
		for(Employment exp: employment_list) {
			working_length_each_employment=0;
			LocalDate start=exp.getStart_date().toLocalDate();
			LocalDate end=exp.getEnd_date().toLocalDate();
			working_length_each_employment=Period.between(start, end).getYears();
			if(exp.isRelevant()) {
				if(point==20) {
					break;
				}
				if(working_length_each_employment>=3 && working_length_each_employment<6) {
					point+=5;
				}
				else if(working_length_each_employment>=6 && working_length_each_employment<9) {
					point+=10;
				}
				else if(working_length_each_employment>=9 && working_length_each_employment<10) {
					point+=15;
				}
				else if(working_length_each_employment>=10) {
					point+=20;
				}
				else {
					point+=0;
				}
			}
		}
		return point;
	}

}

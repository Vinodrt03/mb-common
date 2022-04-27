package com.milkbasket.rest.services.backofficelog.report.validator;

import org.springframework.stereotype.Component;

import com.milkbasket.core.framework.common.constants.CommonFormats;
import com.milkbasket.core.framework.utility.DateUtils;
import com.milkbasket.core.framework.utility.StringUtils;
import com.milkbasket.rest.services.backofficelog.report.bean.AppErrorLogFilterBean;
import com.milkbasket.rest.services.backofficelog.report.bean.AppErrorLogSummaryFilterBean;
import com.milkbasket.rest.services.backofficelog.report.bean.AppLogFilterBean;
import com.milkbasket.rest.services.backofficelog.report.bean.AppLogSummaryFilterBean;
import com.milkbasket.rest.services.backofficelog.report.constant.BackOfficeLogErrors;

@Component
/**
 * <p>
 * CityValidator class.
 * </p>
 *
 * @author NitinKhaitan
 * @version $Id: $Id
 */
public class AppLogSummaryValidator {

	/**
	 * <p>
	 * validate.
	 * </p>
	 *
	 * @param filter
	 *            a
	 *            {@link com.milkbasket.rest.services.backofficelog.report.bean.AppLogSummaryFilterBean}
	 *            object.
	 * @param errors
	 *            a {@link org.springframework.validation.Errors} object.
	 */
	public void validate(final AppLogSummaryFilterBean filter, final org.springframework.validation.Errors errors) {
		if (!errors.hasErrors()) {
			if (StringUtils.isEmpty(filter.getStartDate()) && StringUtils.isNotEmpty(filter.getEndDate())
					|| StringUtils.isNotEmpty(filter.getStartDate()) && StringUtils.isEmpty(filter.getEndDate())) {
				errors.rejectValue("startDate", BackOfficeLogErrors.START_DATE_END_DATE_ERROR);
			}
			if (StringUtils.isNotEmpty(filter.getStartDate()) && StringUtils.isNotEmpty(filter.getEndDate())
					&& DateUtils.getDayDiff(CommonFormats.DATE_FORMAT, filter.getStartDate(), filter.getEndDate()) > 90) {
				errors.rejectValue("startDate", BackOfficeLogErrors.START_DATE_END_DATE_RANGE_ERROR);
			}
		}
	}

	/**
	 * <p>
	 * validate.
	 * </p>
	 *
	 * @param filter
	 *            a
	 *            {@link com.milkbasket.rest.services.backofficelog.report.bean.AppErrorLogSummaryFilterBean}
	 *            object.
	 * @param errors
	 *            a {@link org.springframework.validation.Errors} object.
	 */
	public void validate(final AppErrorLogSummaryFilterBean filter, final org.springframework.validation.Errors errors) {
		if (!errors.hasErrors()) {
			if (StringUtils.isEmpty(filter.getStartDate()) && StringUtils.isNotEmpty(filter.getEndDate())
					|| StringUtils.isNotEmpty(filter.getStartDate()) && StringUtils.isEmpty(filter.getEndDate())) {
				errors.rejectValue("startDate", BackOfficeLogErrors.START_DATE_END_DATE_ERROR);
			}
			if (StringUtils.isNotEmpty(filter.getStartDate()) && StringUtils.isNotEmpty(filter.getEndDate())
					&& DateUtils.getDayDiff(CommonFormats.DATE_FORMAT, filter.getStartDate(), filter.getEndDate()) > 90) {
				errors.rejectValue("startDate", BackOfficeLogErrors.START_DATE_END_DATE_RANGE_ERROR);
			}
		}
	}

	public void validate(final AppLogFilterBean filter, final org.springframework.validation.Errors errors) {
		if (!errors.hasErrors()) {
			if (StringUtils.isEmpty(filter.getStartDate()) && StringUtils.isNotEmpty(filter.getEndDate())
					|| StringUtils.isNotEmpty(filter.getStartDate()) && StringUtils.isEmpty(filter.getEndDate())) {
				errors.rejectValue("startDate", BackOfficeLogErrors.START_DATE_END_DATE_ERROR);
			}
		}
	}

	public void validate(final AppErrorLogFilterBean filter, final org.springframework.validation.Errors errors) {
		if (!errors.hasErrors()) {
			if (StringUtils.isEmpty(filter.getStartDate()) && StringUtils.isNotEmpty(filter.getEndDate())
					|| StringUtils.isNotEmpty(filter.getStartDate()) && StringUtils.isEmpty(filter.getEndDate())) {
				errors.rejectValue("startDate", BackOfficeLogErrors.START_DATE_END_DATE_ERROR);
			}
		}
	}
}

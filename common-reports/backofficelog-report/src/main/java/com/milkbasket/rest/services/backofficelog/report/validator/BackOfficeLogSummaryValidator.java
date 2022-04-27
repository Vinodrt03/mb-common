package com.milkbasket.rest.services.backofficelog.report.validator;

import org.springframework.stereotype.Component;

import com.milkbasket.core.framework.common.constants.CommonFormats;
import com.milkbasket.core.framework.utility.DateUtils;
import com.milkbasket.core.framework.utility.StringUtils;
import com.milkbasket.rest.services.backofficelog.report.bean.BackOfficeLogFilterBean;
import com.milkbasket.rest.services.backofficelog.report.bean.BackOfficeLogSummaryFilterBean;
import com.milkbasket.rest.services.backofficelog.report.bean.DbLogFilterBean;
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
public class BackOfficeLogSummaryValidator {
	/**
	 * validate city for save update
	 *
	 * @param errors
	 *            a {@link org.springframework.validation.Errors} object.
	 * @param filter
	 *            a
	 *            {@link com.milkbasket.rest.services.backofficelog.report.bean.BackOfficeLogSummaryFilterBean}
	 *            object.
	 */
	public void validate(final BackOfficeLogSummaryFilterBean filter, final org.springframework.validation.Errors errors) {
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

	public void validate(final DbLogFilterBean filter, final org.springframework.validation.Errors errors) {
		if (!errors.hasErrors()) {
			if (StringUtils.isEmpty(filter.getStartDate()) && StringUtils.isNotEmpty(filter.getEndDate())
					|| StringUtils.isNotEmpty(filter.getStartDate()) && StringUtils.isEmpty(filter.getEndDate())) {
				errors.rejectValue("startDate", BackOfficeLogErrors.START_DATE_END_DATE_ERROR);
			}
		}
	}

	public void validate(final BackOfficeLogFilterBean filter, final org.springframework.validation.Errors errors) {
		if (!errors.hasErrors()) {
			if (StringUtils.isEmpty(filter.getStartDate()) && StringUtils.isNotEmpty(filter.getEndDate())
					|| StringUtils.isNotEmpty(filter.getStartDate()) && StringUtils.isEmpty(filter.getEndDate())) {
				errors.rejectValue("startDate", BackOfficeLogErrors.START_DATE_END_DATE_ERROR);
			}
		}
	}
}

package com.bonlimousin.gateway.bff;

import io.github.jhipster.service.filter.Filter;

public class UserRoleFilter extends Filter<UserRole> {

    public UserRoleFilter() {
    }

    public UserRoleFilter(UserRoleFilter filter) {
        super(filter);
    }

    @Override
    public UserRoleFilter copy() {
        return new UserRoleFilter(this);
    }

}

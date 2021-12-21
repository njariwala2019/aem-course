package com.myapp.web.core.models;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.InjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import static org.apache.sling.api.resource.ResourceResolver.PROPERTY_RESOURCE_TYPE;

@Model(adaptables = Resource.class)
public class PromoModel {

    @ValueMapValue(name=PROPERTY_RESOURCE_TYPE, injectionStrategy= InjectionStrategy.OPTIONAL)
    @Default(values="No resourceType")
    protected String resourceType;

    @ValueMapValue(name="title", injectionStrategy= InjectionStrategy.OPTIONAL)
    @Default(values="")
    protected String promoTitle;

    public String getResourceType(){
        return this.resourceType;
    }
    public String getPromoTitle(){
        return this.promoTitle != null ? StringUtils.upperCase(this.promoTitle) : "";
    }
}

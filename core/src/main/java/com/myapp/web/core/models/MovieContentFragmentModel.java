package com.myapp.web.core.models;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.via.ChildResource;

import javax.inject.Inject;
import javax.inject.Named;

@Data
@Model(
        adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@Slf4j
public class MovieContentFragmentModel {

    @Inject
    @Named("title")
    @Default(values = "")
    @Via(value = "jcr:content/data/master", type = ChildResource.class)
    private String title;

    @Inject
    @Named("summary")
    @Default(values = "")
    @Via(value = "jcr:content/data/master", type = ChildResource.class)
    private String summary;

    @Inject
    @Named("posterImage")
    @Default(values = "")
    @Via(value = "jcr:content/data/master", type = ChildResource.class)
    private String posterImage;

}

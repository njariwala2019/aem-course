package com.myapp.web.core.models;

import com.day.cq.wcm.api.Page;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(AemContextExtension.class)
class PromoModelTest {

    private PromoModel model;
    private Page page;
    private Resource resource;

    @BeforeEach
    void setUp(AemContext context) {
        page = context.create().page("/content/myappweb");
        Map<String,String> map = new HashMap<>();
        map.put("sling:resourceType", "myAppWeb/components/promo");
        map.put("title","My Promo Title");
        resource = context.create().resource(page,"promo", map);
        model = resource.adaptTo(PromoModel.class);

    }

    @Test
    void getResourceType() {
        String resourceType = model.getResourceType();
        assertEquals("myAppWeb/components/promo",resourceType);
    }

    @Test
    void getPromoTitle() {
        String title = model.getPromoTitle();
        assertEquals("MY PROMO TITLE", title);
    }
}
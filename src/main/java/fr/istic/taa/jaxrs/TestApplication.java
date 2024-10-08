/**
 * JBoss, Home of Professional Open Source
 * Copyright Red Hat, Inc., and individual contributors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fr.istic.taa.jaxrs;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import fr.istic.taa.jaxrs.rest.*;
import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;

public class TestApplication extends Application {

    @Override
    public Set<Object> getSingletons() {
        final Set<Object> singletons = new HashSet<>();
        singletons.add(this);
        singletons.add(new CorsFilter());
        return singletons;
    }

    @Override
    public Set<Class<?>> getClasses() {

        final Set<Class<?>> clazzes = new HashSet<>();

        clazzes.add(UserResource.class);
        clazzes.add(OpenApiResource.class);
        clazzes.add(SwaggerResource.class);
        clazzes.add(TicketResource.class);
        clazzes.add(CommentResource.class);
        clazzes.add(TagResource.class);

        return clazzes;
    }

}

/*
 * Copyright (C) 2020 The Dagger Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package by.andd3dfx.dagger2.coffeemachine;

import dagger.Component;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Singleton;

/**
 * Example of Dagger 2 usage: https://dagger.dev/dev-guide/
 * <p>
 * https://github.com/google/dagger/tree/master/examples/maven/coffee/src/main/java/example/dagger
 * <p>
 * The main app responsible for brewing the coffee and printing the logs.
 */
@Slf4j
public class CoffeeApp {

    @Singleton
    @Component(
        modules = {
            HeaterModule.class,
            PumpModule.class
        }
    )
    public interface CoffeeShop {

        CoffeeMaker maker();

        CoffeeLogger logger();
    }

    public static void main(String[] args) {
        CoffeeShop coffeeShop = DaggerCoffeeApp_CoffeeShop.builder().build();
        coffeeShop.maker().brew();
        coffeeShop.logger().logs().forEach(logEntry -> log.info(logEntry));
    }
}

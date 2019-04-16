
### Akka demo application

During start of application we start several(4) actors of `MigrationActor` type. 
Routing of messages to these actors managed by `BalancingPool`

Scheduler retrieve data items using `DataProvider` (it could be usual dao instead)
and send them one by one to `MigrationActors`

After when actor received message he performs call to `ExternalService`. 
This call takes some time - we perform delay on `ExternalService` side.
It allows to show how to Akka works with multithreading 

To get count of actors use GET operation on http://localhost:8123/actorsCount

To change count of actors use POST operation on http://localhost:8123/actorsCount/{change},
where {change} could be positive or negative

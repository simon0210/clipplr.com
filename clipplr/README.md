## Two Ace Platform Service

### HOW TO RUN?
    ./gradlew two-ace-platform:appRun

### Generate Test DB
#### Check your jdbc url in build.gradle

    flyway {
        driver = 'com.mysql.jdbc.Driver'
        url = 'jdbc:mysql://simon.dev.mango:3306/db_platform'
        ....
    }
#### init & apply    
    ./gradlew two-ace-platform:flywayClean
    ./gradlew two-ace-platform:flywayMigrate
    
### Swagger UI
    http://localhost:8080/two-ace-platform/static/swagger-ui/index.html

### System Monitoring
    http://localhost:8080/two-ace-platform/monitoring
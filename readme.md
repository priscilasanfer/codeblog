### Code Blog

Curso: https://www.youtube.com/watch?v=UdJYuwnqL3I&list=PL8iIphQOyG-AdKMQWtt1bqdVm8QUnX7_S&index=1  
Ministrado por: Michelli Brito  

Projeto disponível na url: http://codeblog-dev222222.us-west-2.elasticbeanstalk.com/posts

Aplicação Spring Boot utilizando Spring MVC.
   - Camada de Segurança: Spring Security;  
   - Renderizar as páginas HTML: Thymeleaf;  
   - Construir layout: Bootstrap;  
   - Banco de dados: PostgreSQL;
   - Deploy: AWS Elastic Beanstalk


## Comandos para deploy

1) Iniciar um app na aws: 
```
$ eb init  
```

2) Criar um RDS Postgres na aws: eb create --scale 1 -db -db.engine postgres -db.i db.t2.micro  

3) Criar um arquivo application-beanstalk.properties:  
```
spring.datasource.url=jdbc:postgresql://${rds.hostname}:${rds.port}/${rds.db.name}  
spring.datasource.username=${rds.username}  
spring.datasource.password=${rds.password}  
``` 

4) Criar um Profile no arquivo pom.xml:
```
    <profiles>
        <profile>
            <id>beanstalk</id>
            <build>
                <finalName>${project.name}-eb</finalName>
                <plugins>
                    <plugin>
                        <groupId>org.springframework.boot</groupId>
                        <artifactId>spring-boot-maven-plugin</artifactId>
                    </plugin>
                    <plugin>
                        <groupId>org.apache.maven.plugins</groupId>
                        <artifactId>maven-compiler-plugin</artifactId>
                        <configuration>
                            <excludes>
                                <exclude>**/cloud/config/*.java</exclude>
                            </excludes>
                        </configuration>
                    </plugin>
                </plugins>
            </build>
        </profile>
    </profiles>
```

5) Gerar o arquivo .jar com o seguinte comando: 
```
$ mvn clean package spring-boot:repackage
```

6) Adicionar o nome do arquivo gerado acima .jar no arquivo de configuração elasticbeanstalk/config.yml: 
```
deploy:
  artifact: target/nome-arquivo-gerado.jar
```

7) Fazer o deploy: 
```
$ eb deploy
```

8) Criar 2 variáveis de ambiente:
```
$ eb setenv SPRING_PROFILES_ACTIVE=beanstalk,mysql
$ eb setenv SERVER_PORT=5000
```

9) Conferir o status na interface aws ou no console: 
```
eb status
```

Utilizando o Postgress localmente com o docker:  

Criar o container: docker run -it --name postgres -e POSTGRES_PASSWORD=root -p 5433:5432 -d postgres  
Acessar o container: docker exec -it postgres bash
Acessando o banco: psql -U postgres  

Comandos básicos: 
acessar a base de dados: \c codeblog   
listar tabelas: \d
descrição da tabela: \d tb_post;

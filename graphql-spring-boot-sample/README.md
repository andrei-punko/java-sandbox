## Use GraphQL in Spring Boot application

Based on: https://github.com/eugenp/tutorials/tree/master/spring-boot-modules/spring-boot-libraries

### Related Article:

[Getting Started with GraphQL and Spring Boot](https://www.baeldung.com/spring-graphql)

### GraphQL sample queries

- Query:

```shell script
curl \
--request POST 'localhost:8081/graphql' \
--header 'Content-Type: application/json' \
--data-raw '{"query":"query {\n    recentPosts(count: 2, offset: 0) {\n        id\n        title\n        author {\n            id\n            posts {\n                id\n            }\n        }\n    }\n}"}'
```

- Mutation:

```shell script
curl \
--request POST 'localhost:8081/graphql' \
--header 'Content-Type: application/json' \
--data-raw '{"query":"mutation {\n    writePost(title: \"New Title\", author: \"Author2\", text: \"New Text\") {\n        id\n        category\n        author {\n            id\n            name\n        }\n    }\n}"}'
```

### Or use GraphiQL web-interface:

http://localhost:8081/graphiql

It was embedded into app with help of next dependency:

```
<dependency>
    <groupId>com.graphql-java</groupId>
    <artifactId>graphiql-spring-boot-starter</artifactId>
    <version>5.0.2</version>
</dependency>
```

Use next queries in web console:

- Query:

```
{
  recentPosts(count: 2, offset: 0) {
    id
    title
    author {
      id
    }
  }
}
```

- Mutation:

```
mutation {
  writePost(title: "New title", author: "Author2", text: "New text") {
    id
    category
    author {
      id
      name
    }
  }
}
```

package com.showmetheroute.persistence.domain.fixture;

import com.mongodb.DBObject;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.index.IndexInfo;

import java.util.Arrays;
import java.util.List;

public class MongoAssertions {
  private MongoOperations ops;

  public static MongoAssertions usingMongo(MongoOperations ops) {
    MongoAssertions assertions = new MongoAssertions();
    assertions.ops = ops;
    return assertions;
  }

  public CollectionAssertions collection(String name) {
    return new CollectionAssertions(name);
  }

  public class CollectionAssertions {
    private String collection;

    CollectionAssertions(String name) {
      this.collection = name;
    }

    public boolean hasIndexOn(String ... fields) {
      List<IndexInfo> indexes = ops.indexOps(collection).getIndexInfo();
      for (IndexInfo info : indexes) {
        if (info.isIndexForFields(Arrays.asList(fields))) {
	  return true;
	}
      }
      return false;
    }

    public DocumentAssertions first() {
      return new DocumentAssertions(ops.getCollection(collection).findOne());
    }

    public class DocumentAssertions {
      DBObject document;

      public DocumentAssertions(DBObject document) {
        this.document = document;
      }

      public boolean hasField(String name) {
        return document.containsField(name);
      }

      public Object fieldContent(String name) {
        return document.get(name);
      }
    }
  }
}

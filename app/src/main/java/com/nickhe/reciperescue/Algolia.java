package com.nickhe.reciperescue;

import com.algolia.search.saas.Client;
import com.algolia.search.saas.Index;

/**
 * Algolia class
 * <p>
 * Contains public fields of client and index for database retrieval purposes
 */
public class Algolia {
    Client client = new Client("670IL2ZO4J", "a3199e7ff8fdcb5ed420d88ee92217be");
    Index index = client.getIndex("dev_recipe");
}

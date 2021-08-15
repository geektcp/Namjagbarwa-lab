package com.geektcp.alpha.db.es6.query;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by nagle on 2018/2/3.
 */
public class SchemaQuery implements Serializable {

    private static final long serialVersionUID = 1L;
    private String schema;
    private float boost = 1.0F;
    private List<BoostField> boostFields = new ArrayList<>();

    public SchemaQuery(String schema) {
        this(schema, 1.0F);
    }

    public SchemaQuery(String schema, float boost) {
        if (StringUtils.isBlank(schema)) {
            new IllegalArgumentException("schema name is null or empty");
        }
        this.schema = schema;
        this.boost = boost <= 0 ? 1.0F : boost;
    }

    /**
     * Added boost fields with default boost(1.0F).
     *
     * @param field
     * @return
     */
    public SchemaQuery addBoostField(String field) {
        this.boostFields.add(new BoostField(field));
        return this;
    }

    /**
     * Added boost field for the relevance boosting score.
     *
     * @param field
     * @param boost
     * @return
     */
    public SchemaQuery addBoostField(String field, float boost) {
        this.boostFields.add(new BoostField(field, boost));
        return this;
    }

    /**
     * Added a boost field to the query
     *
     * @param bf
     * @return
     */
    public SchemaQuery addBoostField(BoostField bf) {
        if (bf == null) {
            throw new IllegalArgumentException("[BoostField] is null");
        }
        this.boostFields.add(bf);
        return this;
    }


    /**
     * Added boost fields with default boost(1.0F).
     *
     * @param fields
     * @return
     */
    public SchemaQuery addBoostFields(Collection<String> fields) {
        if (fields != null){
            for (String field : fields) {
                this.addBoostField(field);
            }
        }
        return this;
    }

    public String getSchema() {
        return schema;
    }

    public float getBoost() {
        return boost;
    }

    public List<BoostField> getBoostFields() {
        return boostFields;
    }
}

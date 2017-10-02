package br.ind.savoy.gerq.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by waltenes on 18/09/17.
 */
public class PaginationBean {

    private Integer currentPage;

    private Integer limit;

    private Long totalResults;

    private SortBean sort;

    private List<FieldsBean> fields;

    private List<? extends Serializable> list;

    public Integer getStart() {
        return currentPage * limit - limit;
    }

    public Integer getEnd() {
        return currentPage * limit;
    }

    public boolean existe(final String chave) {
        final FieldsBean field = getField(chave);
        return field != null && field.getValue() != null && !field.getValue().isEmpty();
    }

    public FieldsBean getField(final String chave) {
        for (final FieldsBean field : getFields()) {
            if (field.getName().equalsIgnoreCase(chave)) {
                return field;
            }
        }
        return null;
    }

    public WhereBuilder where() {
        return new WhereBuilder(this);
    }

    public class WhereBuilder {
        private PaginationBean filtro;
        private List<String> condicoes;

        public WhereBuilder(final PaginationBean filtro) {
            this.filtro = filtro;
            this.condicoes = new ArrayList<>();
        }

        public WhereBuilder presente(final String chave, final String condicao) {
            if (filtro.existe(chave)) {
                condicoes.add(condicao);
            }
            return this;
        }

        public WhereBuilder and(final String condicao) {
            condicoes.add(condicao);
            return this;
        }

        public String build() {
            String where = null;
            for (final String condicao : condicoes) {
                where = (where == null ? " where " : where + " and ") + condicao;
            }
            return where == null ? "" : where;
        }
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Long getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Long totalResults) {
        this.totalResults = totalResults;
    }

    public SortBean getSort() {
        return sort;
    }

    public void setSort(SortBean sort) {
        this.sort = sort;
    }

    public List<FieldsBean> getFields() {
        return fields;
    }

    public void setFields(List<FieldsBean> fields) {
        this.fields = fields;
    }

    public List<? extends Serializable> getList() {
        return list;
    }

    public void setList(List<? extends Serializable> list) {
        this.list = list;
    }
}

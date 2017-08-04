package cn.ggchangan.lucene;

public class TokenInfo {
    private String term;
    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    private int startOffset;
    private int endOffset;
    private String type;

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public int getStartOffset() {
        return startOffset;
    }

    public void setStartOffset(int startOffset) {
        this.startOffset = startOffset;
    }

    public int getEndOffset() {
        return endOffset;
    }

    public void setEndOffset(int endOffset) {
        this.endOffset = endOffset;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "TokenInfo{" +
                "term='" + term + '\'' +
                ", position=" + position +
                ", startOffset=" + startOffset +
                ", endOffset=" + endOffset +
                ", type='" + type + '\'' +
                '}';
    }
}

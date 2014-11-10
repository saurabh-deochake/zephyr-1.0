package negotiation.tree;

import negotiation.db.Attribute;

public class AttributeContainer {
    private Attribute attribute;
    private AttributeContainer parent;
    private String space;
    private boolean expanded = false, visible = false,editable = false;
    private AttributeTree tree;
    private String extendedName;

    public AttributeContainer(AttributeTree tree,Attribute attribute, AttributeContainer parent) {
        this.tree = tree;
        this.attribute = attribute;
        
        if(parent != null) {
            this.parent = parent;
            space = parent.getSpace() + "---";
            if(parent.getParent() == null)
                extendedName = attribute.getName();
            else
                extendedName = parent.getExtendedName() + "." + attribute.getName();
        } else {
            space = "";
            extendedName = "";
        }
    }
    
    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    public AttributeContainer getParent() {
        return parent;
    }

    public void setParent(AttributeContainer parent) {
        this.parent = parent;
    }
    
    public String getSpace() {
        return space;
    }

    public boolean isExpanded() {
        return expanded;
    }
    
    public String getNodeSymbol() {
        return expanded ? "-" : "+";
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
        shrink(this);
    }

    public boolean isVisible() {
        return visible;
    }
    
    public boolean isLinkVisible() {
        return visible & !editable;
    }
    public boolean isNodeVisible() {
        return visible & !editable & hasChildren();
    }
    
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    
    private void shrink(AttributeContainer container) {
        for(AttributeContainer attributeContainer : tree.getAttributeList()) {
            if(attributeContainer.getParent() == container) {
                if(expanded)
                    attributeContainer.setVisible(container.expanded);
                else
                    attributeContainer.setVisible(false);
                shrink(attributeContainer);
            }
        }
    }
    
    public void toggleExpansion() {
        expanded = !expanded;
        shrink(this);
    }
    
    public boolean hasChildren() {
        return attribute.getChildren().size() > 0;
    }

    public boolean isEditable() {
        return editable & visible;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public String getExtendedName() {
        return extendedName;
    }

    public void setExtendedName(String extendedName) {
        this.extendedName = extendedName;
    }
}
package CrystallineApi.Elements;

public abstract class ElementBase {

    public String Name, Id;
    public ElementIcon Icon;

    public ElementBase(String Name, String Id, ElementIcon icon){
        this.Name = Name;
        this.Id = Id;
        this.Icon = icon;
    }

}

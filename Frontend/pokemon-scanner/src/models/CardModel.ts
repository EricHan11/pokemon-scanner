class CardModel {
    //contains what I want to receive back from backend and display to user
    id: number;
    cardName: string;
    setName: string;
    price: number;
    img: string;

    constructor(id: number, cardName: string, setName: string, price: number, img: string) {
        this.id = id;
        this.cardName = cardName;
        this.setName = setName;
        this.price = price;
        this.img = img;
    }
}

export default CardModel;
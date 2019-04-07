export class PaginationBaseModel<T> {

  public empty : boolean;
  public content : T[];
  public numberOfElements : number;
  totalElements : number;
  totalPages : number;
  number : number;



}

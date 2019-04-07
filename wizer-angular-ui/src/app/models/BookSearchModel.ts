export class BookSearchModel {
  pageNo: number;
  pageSize: number;
  title: string;
  categoryName: string;
  author: string;
  isbn: string;

  constructor() {
    this.pageNo = 0;
    this.pageSize = 10;
    this.title = '';
    this.categoryName = '';
    this.author = '';
    this.isbn = '';
  }

}

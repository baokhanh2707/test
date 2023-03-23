export class JwtResponse {
  token: string;
  name: string;
  avatar: string;
  roles: any[];
  idAccount: number;

  constructor(token: string, name: string, avatar: string, roles: any[], idAccount: number) {
    this.token = token;
    this.name = name;
    this.avatar = avatar;
    this.roles = roles;
    this.idAccount = idAccount;
  }
}

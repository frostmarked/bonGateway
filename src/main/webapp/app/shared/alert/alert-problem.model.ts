export class AlertProblem {
  constructor(public message: string, public status?: string, public severity?: string, public key?: string, public params?: any) {}
}

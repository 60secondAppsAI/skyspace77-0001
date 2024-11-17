import http from "../http-common"; 

class VisaService {
  getAllVisas(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/visa/visas`, searchDTO);
  }

  get(visaId) {
    return this.getRequest(`/visa/${visaId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/visa?field=${matchData}`, null);
  }

  addVisa(data) {
    return http.post("/visa/addVisa", data);
  }

  update(data) {
  	return http.post("/visa/updateVisa", data);
  }
  
  uploadImage(data,visaId) {
  	return http.postForm("/visa/uploadImage/"+visaId, data);
  }




	postRequest(url, data) {
		return http.post(url, data);
      };

	getRequest(url, params) {
        return http.get(url, {
        	params: params
        });
    };

}

export default new VisaService();

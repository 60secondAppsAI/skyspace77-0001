import http from "../http-common"; 

class ServiceService {
  getAllServices(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/service/services`, searchDTO);
  }

  get(serviceId) {
    return this.getRequest(`/service/${serviceId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/service?field=${matchData}`, null);
  }

  addService(data) {
    return http.post("/service/addService", data);
  }

  update(data) {
  	return http.post("/service/updateService", data);
  }
  
  uploadImage(data,serviceId) {
  	return http.postForm("/service/uploadImage/"+serviceId, data);
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

export default new ServiceService();

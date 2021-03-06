// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps.servlets;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList; 
import com.google.gson.Gson;
import java.io.PrintWriter;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;

/** Servlet that returns some example content. TODO: modify this file to handle comments data */
@WebServlet("/data")
public class DataServlet extends HttpServlet {



  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    ArrayList<String> comments = new ArrayList<String>();
    Query query = new Query("Comment");
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    PreparedQuery results = datastore.prepare(query);

    for (Entity entity : results.asIterable()) {
      
      comments.add((String) entity.getProperty("comment"));

    }

    
    Gson gson = new Gson();
    response.setContentType("application/json;");

    //response.setContentType("application/json;");
    response.getWriter().println(gson.toJson(comments));

    // response.setContentType("text/html;");
    // PrintWriter out = response.getWriter();
    // Gson gson = new Gson();
    // String json = gson.toJson(comments);
    // out.print(json);
    // out.flush();
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
      String comment = request.getParameter("text-input");

      DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
      Entity commentEntity = new Entity("Comment");
      commentEntity.setProperty("comment", comment);
      datastore.put(commentEntity);
      response.sendRedirect("/index.html");

  }



}

/*
 * Copyright (C) 2016 Dejan
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.pulsiraj.dao.pattern;

import java.util.List;

/**
 *
 * @author Dejan
 */

public interface GenericDAO<T> {
   public Integer createEntity(T ent);
   public T createWeakEntity(T ent);
   public T readEntity(int id);
   public boolean updateEntity(T ent);
   public void deleteEntity(int id);
   public boolean deleteEntity(T ent);
   public List<T> listEntities();
}

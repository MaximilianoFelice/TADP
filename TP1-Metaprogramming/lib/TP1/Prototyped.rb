
module TP

  module Prototyped

    def set_method(selector, block)

    end

    def set_property(selector, value)

    end

    def set_prototype(object)
      self.singleton_class.class_eval{ attr_accessor :prototype }
      self.prototype = object
      self.define_singleton_method(:method_missing, lambda{
                                  |name, *args, &block|
                                  self.prototype.send name, *args, &block })
      self.prototype = object
    end

  end

end

